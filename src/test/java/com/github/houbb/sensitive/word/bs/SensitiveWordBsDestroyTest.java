package com.github.houbb.sensitive.word.bs;

import org.junit.Test;

/**
 * 资源的销毁
 *
 * @since 0.16.0
 */
public class SensitiveWordBsDestroyTest {

    @Test
    public void destroyTest() {
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .init();
        // 后续因为一些原因移除了对应信息，希望释放内存。
        wordBs.destroy();
    }

}
