SELECT h2version();

create schema if not exists paydb;
use paydb;

create table if not exists paydb.payment_base (
    id                     bigint auto_increment                                      not null comment '결제ID' primary key,
    tid                     varchar(50)                                                comment '결제고유번호',
    pg_type                    int(4)                                                     comment 'PG회사코드, 1001:카카오페이',
    payment_type                    int(4)                                                     comment '결제타입, 1001:단건결제 1002:정기결제',
    status                  int(4)                                                     comment '결제상태 1001:생성 1002:성공 1003:실패 1004:취소',
    order_id        bigint                                               comment '주문번호',
    user_id         int(20)                                               comment '회원ID',
    reg_dt                  datetime                                                 null comment '생성 일시',
    upd_dt                  datetime                                                   null comment '수정 일시'
)
engine = innodb;

create table if not exists paydb.payment_results (
    id                             bigint auto_increment                                             comment '결제요청ID' primary key,
    payment_base_id                bigint                                                comment '결제ID(payment_base_id)',
    aid                             varchar(50)                                                 comment '결제요청번호',
    status                    int(4)                                                     comment '결제타입, 1001:결제성공 1002:결제취소 1003:SID발급',
    total_amount                    integer default 0                                           not null comment '전체결제금액',
    tax_free                        integer default 0                                           not null comment '비과세금액',
    vat                             integer default 0                                           not null comment '부가세금액',
    point                           integer default 0                                           not null comment '사용한포인트금액',
    discount                        integer default 0                                           not null comment '할인금액',
    green_deposit                   integer default 0                                           not null comment '컵보증금',
    card_info_id                    bigint                                                      null comment '카드정보ID',
    created_at                      datetime                                                    comment '결제 준비 요청 시각',
    approved_at                     datetime                                                    comment '결제 승인 시각',
    canceled_at                     datetime                                                    comment '결제 취소 시각',
    reg_dt                  datetime                                                 null comment '생성 일시',
    upd_dt                  datetime                                                   null comment '수정 일시'
) engine=InnoDB;

create table if not exists paydb.payment_card_info (
    id                              bigint auto_increment                                                      comment '카드ID' primary key,
    purchase_corp                   varchar(20)                                                 comment '매입 카드사 한글명',
    purchase_corp_code              varchar(10)                                                  comment '매입 카드사 코드',
    issuer_corp                     varchar(20)                                                 comment '카드 발급사 한글명',
    issuer_corp_code                varchar(10)                                                  comment '카드 발급사 코드',
    kakaopay_puchase_corp           varchar(20)                                                 comment '카카오페이 매입사명',
    kakaopay_puchase_corp_code      varchar(10)                                                  comment '카카오페이 매입사 코드',
    kakaopay_issuer_corp            varchar(20)                                                 comment '카카오페이 발급사명',
    kakaopay_issuer_corp_code       varchar(10)                                                  comment '카카오페이 발급사 코드',
    bin                             varchar(15)                                                 comment '카드 BIN',
    card_type                       varchar(20)                                                 comment '카드 타입',
    install_month                   int(2)                                                      comment '할부 개월 수',
    approve_id                      varchar(30)                                                 comment '카드사 승인번호',
    card_mid                        varchar(30)                                                 comment '카드사 가맹점 번호',
    interest_free_install           varchar(1) default 'N'                                      not null comment '무이자할부 여부(Y,N)',
    card_item_code                  varchar(30)                                                 comment '카드 상품 코드',
    reg_dt                  datetime                                                 null comment '생성 일시',
    upd_dt                  datetime                                                   null comment '수정 일시'
    ) engine=InnoDB;
