package java8.lombokdemo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void getterSetter() {
        Member member = new Member();
        member.setName("이름");

        Assert.assertEquals(member.getName(), "이름");
    }
}