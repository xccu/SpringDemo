package com.example.demo.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {// 1

	public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {// 2
		return new CustomRepositoryFactory(entityManager);
	}

	private class CustomRepositoryFactory extends JpaRepositoryFactory {// 3


		public CustomRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
		}

		/*@Override
		@SuppressWarnings({"unchecked"})
		protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
                RepositoryInformation information, EntityManager entityManager) {// 4
			return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);

		}*/

		@Override
		@SuppressWarnings({"unchecked"})
		protected JpaRepositoryImplementation<T, ID> getTargetRepository(RepositoryInformation information,
																		EntityManager entityManager) {
			return new CustomRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {// 5
			return CustomRepositoryImpl.class;
		}
	}
}