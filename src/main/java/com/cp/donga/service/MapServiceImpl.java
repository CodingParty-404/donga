package com.cp.donga.service;

import java.time.LocalDate;
import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.DongaMember;
import com.cp.donga.domain.Picture;
import com.cp.donga.dto.DongaDTO;
import com.cp.donga.repository.DongaRepository;
import com.cp.donga.repository.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private DongaRepository dongaRepository;

    @Override
    public void registerScene() {

    }

    @Override
    public Donga registerDonga(DongaDTO dongaDTO) {
        Donga donga = Donga.builder().eddate(dongaDTO.getEnddate()).stdate(dongaDTO.getStartdate())
                .regdate(LocalDate.now()).title(dongaDTO.getTitle())
                // .member(Member.builder().mid(1L).build())
                .dongamember(DongaMember.builder().id(1L).build()).build();

        return dongaRepository.save(donga);
    }

    @Override
    public List<Picture> getPictures(Long dongaId) {

        Donga donga = Donga.builder().dongaid(dongaId).build();
        return pictureRepository.getPicturesAndWeather(donga);

    }

    @Override
    public List<Picture> registerPictures(List<Picture> list) {
       
        return  pictureRepository.saveAll(list);
    }

    
    
}