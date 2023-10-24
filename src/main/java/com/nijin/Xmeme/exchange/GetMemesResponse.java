package com.nijin.Xmeme.exchange;


import com.nijin.Xmeme.data.MemeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMemesResponse {

    List<MemeEntity> memelist ;

}
