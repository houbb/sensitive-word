package com.github.houbb.sensitive.word.issues;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.tag.WordTags;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Issue131 {

    /**
     * 慢在哪里？
     * 和是否指定没关系，第一次就是慢，要 13ms，为什么？
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        final List<String> allWord = Arrays.asList("敏感","最强","定制", "81", "医疗器械");
        String demo1 = "产品尺寸参数§60mn§50mm§210枚/包§160枚/包§名称A4银色不干胶§规格60mm*40mm 送配套模板§规格70mm*50mm 送配套模板§数量每大张21枚一包10张总计210枚§数量每大张16枚一包10张总计160枚§适用激光打印机打印油性笔书写§95mm§100mn§55mm§100枚/包§80枚/包§名称 A4银色不干胶§规格95mm*55mm 送配套模板§规格100mm*70mm 送配套模板§数量每大张10枚一包10张总计100枚§数量 每大张8枚一包10张 总计80枚§100mm§120枚/包§140枚/包§规格80mm*50mm 送配套模板§规格100mm*40mm 送配套模板§数量每大张12枚一包10张总计120枚§数量§每大张14枚包10张总计140枚§适用 激光打印机打印油性笔书写§40mm§65mm§70mm§35mm§200枚/包§240枚/包§规格70mm*40mm送配套模板§规格§65mm*35mm 送配套模板§数量 每大张20枚一包10张总计200枚§每大张24枚包10张总计240枚§适 激光打印机打印油性笔书写§适用§激光打印机打印油性笔书写§40mn§280枚/包§360枚/包§规格50mm*40mm 送配套模板§规格40mm*30mm 送配套模板§数量每大张28枚一包10张总计280枚§数量每大张36枚一包10张总计360枚§45.7mm§38.1mm§400枚/包§650枚/包§45.7mm*25.4mm送配套模板§38.1mm*21.2mm 送配套模板§每大张40枚一包10张总计400枚§数量每大张65枚一包10张总计650枚§30mm§25mr§20mm§840枚/包§1260枚/包§规格 30mm*20mm 送配套模板§规格25mm*13mm 送配套模板§数量每张84枚包10张总计840枚§数量每大张126枚一包10张总计1260枚§46mm§意制§任§1000枚/包§定§名称定制A4内割银不胶§规格46mm*11.1mm送配套模板§任意规格定制§每大张100枚包10张总计1000枚§包10张满5包送专属模板§适激光打印机打印油性笔书写§产品实拍§8格打印实拍展示(100mm*70mm)§上海荠骞文化用品固定资产标识卡§资产编号：§规格型号：§资产名称：§使用状态：§资产类别：§资产原值§存放地点§生产厂家：§使用人§备§注：§*请爱护公司财产，不要随意撕毁此标签§16格全内容打印实拍展示§固定资产标识卡§资产名称§四层货架（平板）§资产编号§3F跑菜区§规格型号§1800×500×1500§使用部门§财务部§使用时间§2019-04-26§李强§21格手写款打印展示 (60mm*40mm)§固定资标识卡§36格打印实拍展示(40mm*30mm)§固定资产标签§名称:§编号:§部门:§40格打印实拍展示(45.7mm*25.4mm)§固定资§名称：电脑§编号：20210§部门：财务部§20210201§使用人：我最强§八：找最强§编号：20210201§65格打印实拍展示(38mm*21mm)§名称：§编号：§数量：§数量:§100格打印实拍展示(46mm*11.1mm)§客服电话：159 9569 3815§: 159 9569 3815§.§客服电话：159 9569§客服电话：1599§客服电话§服电话：159 9569 3815§话：159 9569 3815§客服电话：1599569 3815§电话：159 9569 3815§9569 3815§159 9569 3815§客服电话：§低值易耗品标识牌(70mm*50mm)§购买日期§保管部门§责任人§生产厂家§不要随意撕毁此标牌*§*请爱护公司财产，不要随意撕导§品标识牌§低值易耗品标识牌§随意撕毁此标牌*§*请爱护公司财产，不要随意撕毁此标牌*§三人沙发§行政酒廊§2200*860*900§2018-07-23§应用范围§多用于产品信息固有资产登记航空仓库管理 医疗政府机构等§Mainly used for product information inherent assets registration, aviation warehouse management, medi§cal government institutions, etc§政府单位§企业办公§仓储行业§医疗器械§教育单位§耐用品§电子产品包装§商城卖场";

        // 初始化敏感词库
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordFailFast(true)
                .wordAllow(WordAllows.empty())
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return allWord;
                    }
                })
                .ignoreChineseStyle(false)
                .ignoreCase(false)
                .ignoreEnglishStyle(false)
                .ignoreNumStyle(false)
                .ignoreRepeat(false)
                .ignoreWidth(false)
                .wordTag(WordTags.none())
                .init();
        long time = System.currentTimeMillis();
        costTimeTest(sensitiveWordBs, demo1);
        long cTime = System.currentTimeMillis() - time;
        System.out.println("---DONE"+cTime);
    }

    private static void costTimeTest(SensitiveWordBs sensitiveWordBs, String demo1) throws IOException {
        int count = 10000;

        for (int i = 0; i < count; i++) {
            List<String> emitWord1 = sensitiveWordBs.findAll(demo1);
        }
    }

}
