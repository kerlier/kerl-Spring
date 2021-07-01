package com.fashion.spring.event;

import com.fashion.spring.config.ConfigDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 10:27
 */
public class FashionEvent extends ApplicationEvent {
    public FashionEvent(ConfigDTO source) {
        super(source);
    }

    @Override
    public ConfigDTO getSource(){
        return (ConfigDTO) super.getSource();
    }
}
