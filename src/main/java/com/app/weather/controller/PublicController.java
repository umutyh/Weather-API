package com.app.weather.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;


@RestController
@RequestMapping("public")
public class PublicController {

    @RequestMapping(value = "server-time", method = RequestMethod.GET)
    public HashMap<String, Object> test() {

        HashMap<String,Object> serverTime = new HashMap<>();
        serverTime.put("serverTime",Calendar.getInstance().getTimeInMillis());
        serverTime.put("name","umut");


        try {
            Barcode barcode = BarcodeFactory.createEAN13("978020137962");


            BufferedImage image = BarcodeImageHandler.getImage(barcode);
                    File outputFile = new File("C:\\Users\\ummut\\OneDrive\\Desktop\\BARCODE.png"); // replace with your desired output file path

            try {
                ImageIO.write(image, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

//        generateQRCode();

        return serverTime;
    }


    private void generateQRCode(String qrCodeData){
        qrCodeData = "https://google.com"; // Replace with your data
        String filePath = "C:\\Users\\ummut\\OneDrive\\Desktop\\qrcode.png"; // Replace with your desired output file path
        int width = 500; // Replace with your desired image width
        int height = 500; // Replace with your desired image height
        String fileType = "png"; // Replace with your desired image file type

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            File outputFile = new File(filePath);
            ImageIO.write(bufferedImage, fileType, outputFile);
            System.out.println("QR code generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "city", method = RequestMethod.GET)
    public HashMap<String, Object> test(@RequestParam("site") String site) {

        HashMap<String,Object> serverTime = new HashMap<>();
        serverTime.put("serverTime",Calendar.getInstance().getTimeInMillis());
        generateQRCode(site);
        return serverTime;
    }

}