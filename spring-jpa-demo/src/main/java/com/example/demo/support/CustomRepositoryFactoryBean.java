package com.example.demo.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 定义RepositoryFactoryBean
 * @param <T>
 * @param <S>
 * @param <ID>
 */
public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {// 1 继承JpaRepositoryFactoryBean类

	public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	/**
	 * 2 重写createRepositoryFactory方法,返回CustomRepositoryFactory对象
	 * @param entityManager
	 * @return
	 */
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new CustomRepositoryFactory(entityManager);
	}

	/**
	 *  3 内部类继承JpaRepositoryFactory
	 */
	private class CustomRepositoryFactory extends JpaRepositoryFactory {

		public CustomRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		/*@Override
		@SuppressWarnings({"unchecked"})
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
                RepositoryInformation information, EntityManager entityManager) {// 4
			return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);

		}*/

		/**
		 * 重写getTargetRepository方法，返回自定义接口实现类 CustomRepositoryImpl
		 * @param information
		 * @param entityManager
		 * @return
		 */
		@Override
		@SuppressWarnings({"unchecked"})
		protected JpaRepositoryImplementation<T, ID> getTargetRepository(RepositoryInformation information,
																		EntityManager entityManager) {
			return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
		}

		/**
		 * 5 重写getRepositoryBaseClass方法
		 * @param metadata
		 * @return
		 */
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return CustomRepositoryImpl.class;
		}
	}
}