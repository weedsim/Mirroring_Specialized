package com.a306.fanftasy.domain.user.service;

import com.a306.fanftasy.domain.board.dto.RequestArticleBoard;
import com.a306.fanftasy.domain.board.repository.BoardRepository;
import com.a306.fanftasy.domain.user.entity.User;
import com.a306.fanftasy.domain.user.repository.UserRepository;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public void saveUploadFile(User user, MultipartFile multipartFile) throws IOException, java.io.IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String originalFilename = multipartFile.getOriginalFilename(); // test.PNG
        int index = originalFilename.lastIndexOf("."); // 4
        String ext = originalFilename.substring(index + 1); // PNG

        String storeFileName = UUID.randomUUID() + "." + ext; // random + . + PNG
        String key = "profileImg/" + storeFileName; // 파일 위치(test/) + 및 파일명
        System.out.println("storeFileName : " + storeFileName);
        System.out.println("key : " + key);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }

        String storeFileUrl = amazonS3Client.getUrl(bucket, key).toString(); // 버켓 url/ + key(파일위치 + 파일명)
        user.setProfileImg(storeFileUrl);
        userRepository.save(user);
    }

}
