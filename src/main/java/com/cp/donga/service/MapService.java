package com.cp.donga.service;

import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Picture;
import com.cp.donga.dto.DongaDTO;

/**
 * MapService
 */

public interface MapService {



    public Donga registerDonga(DongaDTO dongaDTO);

    public List<Picture> getPictures( Long dongaId );

    public List<Picture> registerPictures( List<Picture> list );
    
    public void registerScene();
}