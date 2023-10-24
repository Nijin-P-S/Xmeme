package com.nijin.Xmeme.data;


import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "memes")
public class MemeEntity {

  @Id
  String id;

  @NonNull
  String name;

  @NonNull
  String url;

  @NonNull
  String caption;

}
