package com.basic.spring.bean.post.component;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/5 14:28
 */
//@Component("myOwnComponent")
public class MyOwnComponent {
//    @Autowired
//    private My2Component component;

    public MyOwnComponent(){
        System.out.println("初始化bean");
    }

    private String componentName = "10";

    private String componentPassword;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentPassword() {
        return componentPassword;
    }

    public void setComponentPassword(String componentPassword) {
        this.componentPassword = componentPassword;
    }

//    public My2Component getComponent() {
//        return component;
//    }
//
//    public void setComponent(My2Component component) {
//        this.component = component;
//    }
}
