package com.maid.service.provider.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {


    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile image , String fileName)  {
        try {

            String  folderName = "Maid_Service";
            String fullPublicId = folderName + "/" + fileName;
            System.out.println("fullPublicId = " + fullPublicId);
            Map<String, Object> uploadParams = ObjectUtils.asMap(
                    "public_id", fullPublicId,
                    "overwrite", true
                    //"type", "authenticated"  // 🔐 Make it private (non-public)

            );
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), uploadParams);
            String publicId = (String) uploadResult.get("public_id");

            // Use the Cloudinary URL generation method with transformation
            String imageUrl = cloudinary.url()
                    .transformation(new Transformation()
                            .width(1000)     // Resize the image to width 1000px
                            .height(1000)    // Optionally set a height
                            .crop("fill")    // Ensure the image fills the target size
                            .quality("auto") // Automatically adjust the quality for compression
                            .fetchFormat("auto")  // Automatically select the best format (e.g., WebP, JPEG)
                    )
                    .generate(publicId);  // Use the public_id to generate the URL

            return imageUrl;  // Return the transformed image URL

        } catch (IOException e) {
            e.printStackTrace();
            return null;  // In case of error, return null
        }
    }


    //  no one can directly access url
//    String signedUrl = cloudinary.url()
//            .secure(true)
//            .signed(true)
//            .version("1.0")  // Optional: from DB or upload result
//            .generate("Maid_Service/profile_1234");

}
