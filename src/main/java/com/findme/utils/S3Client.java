package com.findme.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.util.IOUtils;
import com.findme.exception.BadRequestException;

@Component
public class S3Client {

	private final Logger logger = LoggerFactory.getLogger(S3Client.class);

	@Value("${aws.bucketname}")
	private String awsBucketName;

	private AmazonS3 s3Client;

	@Autowired
	public S3Client(@Value("${aws.accesskey}") final String awsAccessKey,
			@Value("${aws.secretkey}") final String awsSecretKey) {
		BasicAWSCredentials creds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
		s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
				.withRegion(Region.AP_Sydney.toString()).build();
	}

	public URL uploadImageToBucket(String name, MultipartFile file) throws BadRequestException {

		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read in as bytearray before checking as it consumes the is
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

		// checkImageType(is);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(bytes.length);

		s3Client.putObject(new PutObjectRequest(awsBucketName, name, byteArrayInputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));

		URL artifactUrl = s3Client.getUrl(awsBucketName, name);

		logger.debug("Uploaded s3 artifact. Artifact available at URL: " + artifactUrl);

		return artifactUrl;

	}

	// TODO fix up so that file types get picked up properly
	// private void checkImageType(InputStream is) throws BadRequestException {
	//
	// InputStream bi = new BufferedInputStream(is);
	//
	// AutoDetectParser parser = new AutoDetectParser();
	// Detector detector = parser.getDetector();
	// Metadata md = new Metadata();
	// MediaType mediaType = null;
	// try {
	// mediaType = detector.detect(bi, md);
	// logger.debug("user attempting to upload media of type: " +
	// mediaType.getSubtype());
	// if (!mediaType.getSubtype().equals("jpeg")) {
	// throw new BadRequestException("bad file type - ONLY jpeg is supported");
	// }
	// } catch (IOException e) {
	// throw new BadRequestException("unexpected input in uploaded file");
	// } finally {
	// try {
	// bi.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// }
}
