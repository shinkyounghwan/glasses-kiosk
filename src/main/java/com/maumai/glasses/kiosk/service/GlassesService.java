package com.maumai.glasses.kiosk.service;

import com.maumai.glasses.kiosk.entity.Glasses;
import com.maumai.glasses.kiosk.entity.User;
import com.maumai.glasses.kiosk.repository.GlassesRepository;
import com.maumai.glasses.kiosk.repository.UserRepository;
import com.maumai.glasses.kiosk.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GlassesService {

    private final GlassesRepository glassesRepository;
    private final UserRepository userRepository;


    @Transactional
    public ResponseEntity<Response<List<Glasses>>> getGlassesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. ID: " + userId));

        List<Glasses> glassesList = glassesRepository.findByUser(user);
        Response<List<Glasses>> response = new Response<>("성공", "안경 데이터 조회 성공", glassesList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}