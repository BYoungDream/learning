package com.elitel.elitelswaggerdoctools.controller;

import com.elitel.elitelswaggerdoctools.common.AsciidoctorHelper;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.asciidoctor.Asciidoctor.Factory.create;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * created by guoyanfei on 2019/3/13
 */
@RestController
@RequestMapping("/home")
public class HomeIndex {
    //创建Asciidoctor
    Asciidoctor asciidoctor = create();
    /**
     * show 通过swagger api生成adoc文档
     * create by guoyanfei on 2019/03/13
     */
    @RequestMapping("/getdocs")
    public void generateAsciiDocs(){
        //    输出Ascii到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();
        try {

            Swagger2MarkupConverter.from(new URL("http://127.0.0.1:8888/v2/api-docs"))
                    .withConfig(config)
                    .build()
                    .toFile(Paths.get("src/docs/asciidoc/generated/all"));

        } catch (MalformedURLException mex){
            System.out.println(mex.getMessage());
        }






    }

    /**
     * show 通过adoc生成 html文件
     */
    @RequestMapping("/gethtml")
    public void generateHtml(){
        //文档属性
        AttributesBuilder attributesBuilder = AttributesBuilder.attributes().tableOfContents(false);
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("doctype", "book");
        attributes.put("toc", "left");//左侧
        attributes.put("toclevels", "3");//三级菜单
        AsciidoctorHelper.addAttributes(attributes, attributesBuilder);

        AsciidoctorHelper.setAttributesOnBuilder(attributesBuilder);

        OptionsBuilder optionsBuilder = OptionsBuilder.options();
        String outputpath = "src/docs/asciidoc/html";
        AsciidoctorHelper.setOptionsOnBuilder(optionsBuilder,outputpath);

        optionsBuilder.attributes(attributesBuilder);
        //生成html文件
        String tt = asciidoctor.convertFile(
                new File(Paths.get("src/docs/asciidoc/generated/all.adoc").toString()),
                optionsBuilder);


    }





}
