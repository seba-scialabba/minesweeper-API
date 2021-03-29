package com.minesweeper.domain.minefield;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

@Component
public class MinefieldFactory implements BeanFactoryAware {
	private BeanFactory beanFactory;

	public Minefield createMinefield(MinefieldConfig config) {
		return (Minefield) beanFactory.getBean(config.getType().name(), config);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
