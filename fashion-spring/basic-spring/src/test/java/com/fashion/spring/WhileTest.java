package com.fashion.spring;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/26 19:16
 */
public class WhileTest {
    public static void main(String[] args) {
        PersonDate personDate = new PersonDate();
        int page = 1;
        AtomicInteger size1 = new AtomicInteger();

        while (true) {
            personDate.setPage(page);
            System.out.println(personDate.getPage());
            if (page > 10) {
                break;
            }
            size1.addAndGet(111);
            page++;
        }
        System.out.println(size1.get());
        String a = "a";
        String intern = a.intern();

    }
}
