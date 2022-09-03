package org.hothyojas.mogayobackend.controllers;

import lombok.RequiredArgsConstructor;
import org.hothyojas.mogayobackend.services.FirebaseCloudMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
public class ddController {

    private final FirebaseCloudMessageService firebaseCloudMessageService;

    @GetMapping("/dd")
    public void pushMessage() {

        String title = "주환님 잘생겼다";
        String body = "FCM test 입니다아아아ㅏ아ㅏㅏㅏㅏ아악";
        String token = "dywpmqVkQwug-C57mHKtBy:APA91bFQz2zp7dH_6ee_Bzas92rhDMXUPDuCafH5bzDUx4bTnPaEdPp_NflMbfJWptpTmNRK4_VTx2MSFDx2hjsr9u15_Eq70XmdnnX3k493jTyAIHlYu-bimopA6EMEivmvfMPFEGnt";

        firebaseCloudMessageService.sendMessageTo(token, title, body);

    }

}
