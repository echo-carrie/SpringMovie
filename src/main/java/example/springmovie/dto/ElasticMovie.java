package example.springmovie.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "movies")
public class ElasticMovie {

    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    // 其他字段和getter/setter方法
}