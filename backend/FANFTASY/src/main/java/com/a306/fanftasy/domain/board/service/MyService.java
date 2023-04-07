package com.a306.fanftasy.domain.board.service;

import com.a306.fanftasy.domain.board.dto.RequestArticleBoard;
import com.a306.fanftasy.domain.board.entity.Board;
import com.a306.fanftasy.domain.board.repository.BoardRepository;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyService {

    private final ImageService imageService;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        File tempFile = File.createTempFile(fileName, null);
        file.transferTo(tempFile);
        String imageUrl = imageService.uploadImage(tempFile, fileName);
        RequestArticleBoard requestArticleBoard = new RequestArticleBoard();
        requestArticleBoard.setFilePath(imageUrl);
        User user = userRepository.findById(1L).orElse(null);
        boardRepository.save(requestArticleBoard.toEntity(user));
        return imageUrl;
    }

//    public List<Image> getImages() {
//        return imageRepository.findAll();
//    }
}
