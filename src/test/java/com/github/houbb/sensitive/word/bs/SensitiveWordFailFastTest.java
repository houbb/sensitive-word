package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @since 0.26.0
 */
public class SensitiveWordFailFastTest {

    @Test
    public void failFastTest() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .wordFailFast(true)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();

        SensitiveWordBs bs1 = SensitiveWordBs.newInstance()
                .wordFailFast(true)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("操你妈");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("你");
                    }
                })
                .init();


        //黑长白短，且初始下标一致
        SensitiveWordBs bs2 = SensitiveWordBs.newInstance()
                .wordFailFast(true)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("大傻逼");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("大");
                    }
                })
                .init();



        //白长黑短，且白和黑初始下标不再一起
        SensitiveWordBs bs3 = SensitiveWordBs.newInstance()
                .wordFailFast(true)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("口交");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("地铁口交易");
                    }
                })
                .init();


        //白长黑短，且白和黑初始下标在一起
        SensitiveWordBs bs4 = SensitiveWordBs.newInstance()
                .wordFailFast(true)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("龟孙");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("龟孙可");
                    }
                })
                .init();






        String text = "我在我的家里玩我的世界";
        List<String> textList = bs.findAll(text);
        Assert.assertEquals(Arrays.asList("我的", "我的"), textList);


        String text1 = "操你妈";
        List<String> textList1 = bs1.findAll(text1);
        Assert.assertEquals(Collections.singletonList("操你妈"), textList1);

        String text2 = "大傻逼";
        List<String> textList2 = bs2.findAll(text2);
        Assert.assertEquals(Collections.singletonList("大傻逼"), textList2);


        String text3 = "地铁口交易";
        List<String> textList3 = bs3.findAll(text3);
        Assert.assertTrue("Expected empty list", textList3.isEmpty());

        String text4 = "龟孙可";
        List<String> textList4 = bs4.findAll(text4);
        Assert.assertTrue("Expected empty list", textList4.isEmpty());


    }


    @Test
    public void fallOverTest() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();


        //黑长白短，且初始下标不一致
        SensitiveWordBs bs1 = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("操你妈");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("你");
                    }
                })
                .init();


        //黑长白短，且初始下标一致
        SensitiveWordBs bs2 = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("大傻逼");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("大");
                    }
                })
                .init();



        //白长黑短，且白和黑初始下标不再一起
        SensitiveWordBs bs3 = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("口交");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("地铁口交易");
                    }
                })
                .init();


        //白长黑短，且白和黑初始下标在一起
        SensitiveWordBs bs4 = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("龟孙");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("龟孙可");
                    }
                })
                .init();






        String text = "我在我的家里玩我的世界";
        List<String> textList = bs.findAll(text);
        Assert.assertEquals(Arrays.asList("我的", "我的世界"), textList);


        String text1 = "操你妈";
        List<String> textList1 = bs1.findAll(text1);
        Assert.assertEquals(Collections.singletonList("操你妈"), textList1);

        String text2 = "大傻逼";
        List<String> textList2 = bs2.findAll(text2);
        Assert.assertEquals(Collections.singletonList("大傻逼"), textList2);


        String text3 = "地铁口交易";
        List<String> textList3 = bs3.findAll(text3);
        Assert.assertTrue("Expected empty list", textList3.isEmpty());

        String text4 = "龟孙可";
        List<String> textList4 = bs4.findAll(text4);
        Assert.assertTrue("Expected empty list", textList4.isEmpty());


    }

    @Test
    public void fallOverTest2() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();

        String text = "他的世界它的世界和她的世界都不是我的也不是我的世界";
        List<String> textList = bs.findAll(text);
        Assert.assertEquals(Arrays.asList("我的", "我的世界"), textList);

        SensitiveWordBs bs2 = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();

        List<String> textList2 = bs2.findAll(text);
        Assert.assertEquals(Arrays.asList("我的", "我的"), textList2);
    }

    @Test
    public void fallOverTest3() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .wordFailFast(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界");
                    }
                }).init();

        String text = "他的世界它的世界和她的世界都不是我的也不是我的天界";
        List<String> textList = bs.findAll(text);
        Assert.assertTrue(CollectionUtil.isEmpty(textList));

        SensitiveWordBs bs2 = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界");
                    }
                }).init();

        List<String> textList2 = bs2.findAll(text);
        Assert.assertTrue(CollectionUtil.isEmpty(textList2));
    }

}
