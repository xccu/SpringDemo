package com.example.demo.specs;

import static com.google.common.collect.Iterables.toArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class CustomerSpecs {

    /**
     * 1 定义一个返回值为Specification的方法 byAuto使用泛型T
     */
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {

        //2 获取当前实体类对象类的模型
        final Class<T> type = (Class<T>) example.getClass();

        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //3 新建Predicate列表存储构造的查询条件
                List<Predicate> predicates = new ArrayList<>();

                //4 获取实体类的Entitytype
                EntityType<T> entity = entityManager.getMetamodel().entity(type);

                //5 对实体类的所有属性做循环
                for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
                    //6 获取属性的值
                    Object attrValue = getValue(example, attr);
                    if (attrValue != null) {
                        //7 当前属性类型为String时
                        if (attr.getJavaType() == String.class) {
                            //8 String不为空
                            if (!StringUtils.isEmpty(attrValue)) {
                                //9 构造当前属性like，属性值为查询条件，并加入条件列表中
                                predicates.add(
                                        cb.like(
                                                root.get(attribute(entity, attr.getName(), String.class)),
                                                pattern((String) attrValue)
                                        )
                                );
                            }
                        } else {
                            //10 其余情况下，构造属性和属性值equals查询条件，并加入条件列表中
                            predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())),
                                    attrValue));
                        }
                    }

                }
                //11 将其余条件列表转换成Predicate
                return predicates.isEmpty() ? cb.conjunction() : cb.and(toArray(predicates, Predicate.class));
            }

            /**
             * 12 通过反射获取实体类对象和属性值
             */
            private <T> Object getValue(T example, Attribute<T, ?> attr) {
                return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
            }

            /**
             * 13 获取单个属性
             */
            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
                                                             Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }

        };

    }

    /**
     * 14 构造like查询模式，即前后加"%"
     */
    static private String pattern(String str) {
        return "%" + str + "%";
    }

}
