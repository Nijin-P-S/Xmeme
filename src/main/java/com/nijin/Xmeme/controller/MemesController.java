package com.nijin.Xmeme.controller;


import com.nijin.Xmeme.data.MemeEntity;
import com.nijin.Xmeme.exchange.GetMemesResponse;
import com.nijin.Xmeme.exchange.PostMemeResponse;
import com.nijin.Xmeme.service.MemesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class MemesController {

  @Autowired
  private MemesService mservice;

  @PostMapping("/memes/")
  public ResponseEntity<PostMemeResponse> StoreMeme(@Valid @RequestBody MemeEntity meme) {

    boolean isPresent = mservice.addMeme(meme);

    PostMemeResponse response = new PostMemeResponse(meme.getId());

    if (isPresent) return new ResponseEntity<>(HttpStatus.CONFLICT);
    else return new ResponseEntity<>(response, HttpStatus.CREATED);
  }


  @GetMapping("/memes/")
  public List<MemeEntity> GetAllMemes() {

    GetMemesResponse memelistobj = mservice.getMemes();

    return memelistobj.getMemelist();

  }


  @GetMapping("/memes/{id}")
  public ResponseEntity<MemeEntity> GetMemeById(@PathVariable String id) {

    Optional<MemeEntity> memeInfo = mservice.getMeme(id);

    return memeInfo.map(memeEntity -> new ResponseEntity<>(memeEntity, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
