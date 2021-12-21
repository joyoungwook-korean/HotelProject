package com.springboot.st.signupProject.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.springboot.st.hotelProject.domain.Hotel_Room_Img;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@NoArgsConstructor
public class S3Service {
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;


    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile file,String server_name) throws IOException{
        ObjectMetadata objMeta = new ObjectMetadata();
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        objMeta.setContentLength(bytes.length);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        amazonS3.putObject(new PutObjectRequest(bucket,server_name,byteArrayInputStream,null)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3.getUrl(bucket,server_name).toString();
    }

    public void delete_Img(String serverPath){
        amazonS3.deleteObject(bucket,serverPath);

    }

    public Map<String, String> serverSaveMap(MultipartFile multipartFile){
        Map<String, String> serverSave = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        String origin_name = multipartFile.getOriginalFilename();
        String server_name = uuid + origin_name;

        String server_url=null;
        try {
            server_url = upload(multipartFile,server_name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverSave.put("uuid", uuid);
        serverSave.put("originName",origin_name);
        serverSave.put("serverName",server_name);
        serverSave.put("serverURL",server_url);

        return serverSave;
    }


}
