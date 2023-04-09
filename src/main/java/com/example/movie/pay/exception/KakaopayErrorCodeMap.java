package com.example.movie.pay.exception;

import java.util.HashMap;
import java.util.Map;

public class KakaopayErrorCodeMap {
    private static final Map<String, String> map;

    static  {
        map = new HashMap<>();
        map.put("-701","결제 인증이 완료되지 않은 상태에서 결제 승인 API를 호출한 경우");
        map.put("-702","이미 결제 완료된 TID로 다시 결제승인 API를 호출한 경우");
        map.put("-703","결제 승인 API 호출 시 포인트 금액이 잘못된 경우");
        map.put("-704","결제 승인 API 호출 시 결제 금액이 잘못된 경우");
        map.put("-705","결제 승인 API 호출 시 CARD 또는 MONEY 외에 지원하지 않는 결제 수단으로 요청한 경우");
        map.put("-706","결제 준비 API에서 요청한 partner_order_id와 다른 값으로 결제승인 API 호출한 경우");
        map.put("-707","결제 준비 API에서 요청한 partner_user_id와 다른 값으로 결제승인 API 호출 한 경우");
        map.put("-708","잘못된 pg_token로 결제승인 API를 호출한 경우");
        map.put("-710","결제 취소 API 호출 시 취소 요청 금액을 취소 가능액보다 큰 금액으로 요청한 경우");
        map.put("-721","TID가 존재하지 않는 경우");
        map.put("-722","금액 정보가 잘못된 경우");
        map.put("-723","결제 만료 시간이 지난 경우");
        map.put("-724","단건 결제 금액이 잘못된 경우");
        map.put("-725","총 결제 금액이 잘못된 경우");
        map.put("-726","주문 정보가 잘못된 경우");
        map.put("-730","가맹점 앱 정보가 잘못된 경우");
        map.put("-731","CID 가 잘못된 경우");
        map.put("-732","GID 가 잘못된 경우");
        map.put("-733","CID_SECRET이 잘못된 경우");
        map.put("-750","SID가 존재하지 않는 경우");
        map.put("-751","비활성화된 SID로 정기 결제 API를 호출한 경우");
        map.put("-752","SID가 월 최대 사용 회수를 초과한 경우");
        map.put("-753","정기 결제 API 호출 시 partner_user_id가 SID를 발급받았던 최초 결제 준비 API에서 요청한 값과 다른 경우");
        map.put("-761","입력한 전화번호가 카카오톡에 가입하지 않은 경우");
        map.put("-780","결제 승인 API 호출이 실패한 경우");
        map.put("-781","결제 취소 API 호출이 실패한 경우");
        map.put("-782","정기 결제 API 호출이 실패한 경우");
        map.put("-783","승인 요청을 할 수 없는 상태에서 결제 승인 API를 호출한 경우");
        map.put("-784","취소 요청을 할 수 없는 상태에서 결제 취소 API를 호출한 경우");
        map.put("-785","결제와 취소를 중복으로 요청한 경우");
        map.put("-797","1회 결제 한도 금액을 초과할 경우");
        map.put("-798","허용되지 않는 IP를 사용한 경우");
        map.put("-799","등록된 웹사이트 도메인의 설정과 요청 도메인이 다를 경우 해결 방법: [내 애플리케이션] > [플랫폼] > [Web]에서 등록한 사이트 도메인 확인");
    }

    public static String getErrorMsg(String code) {
        return map.getOrDefault(code,"등록되지 않은 code 입니다. code:" + code);
    }
}
