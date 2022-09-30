package com.astar.realty.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

//custom exception errocode 정의
@AllArgsConstructor
@Getter
public enum ErrorCode {

    // old > INVALID_FIELD
    INVALID_ID(400, "BAD_REQUEST", "B002", "Broker account Id is invalid"),
    INVALID_PWD(400, "BAD_REQUEST", "B003", "Broker account Pwd is invalid"),
    INVALID_BROKER_OFFICE(400, "BAD_REQUEST", "B000", "Broker office is not exist"),
    INVALID_ADDRESS(400,"BAD_REQUEST", "R004", "realty address is invalid"),
    NOT_EXISTED_BUILDING(200,"No content", "R002", "building is not existed"),
    
    // invalid argument error
    INVALID_FIELD(400,"BAD_REQUEST","A001","Arguments are invalid"),

    // Broker
    ID_DUPLICATTION(400,"BAD_REQUEST", "B000", "Broker account Id is duplicated"),
    AES_ALGORITHM_ERROR(500,"INTERNAL_SERVER_ERROR", "B000", "Password encryption is failed"),
    INCORRECT_LOGIN_ARGUMENT(401, "UNAUTHORIZED", "B000", "Broker login argument(id or pwd) is incorrect"),
    NOT_EXIST_BROKER_ID(400, "BAD_REQUEST", "B000", "Broker Id is not exist"),
    NOT_EXIST_BROKER_NO(400, "BAD_REQUEST", "B000", "Broker number is not exist"),
    INVALID_STATUS(400,"BAD_REQUEST", "B006", "Parameter(status) is invalid [1,2,3,4]"),
    INVALID_GRADE(400,"BAD_REQUEST", "B007", "Parameter(value) is invalid [broker,admin]"),

    BROKER_OFFICE_DUPLICATION(400,"BAD_REQUEST", "B000", "Broker office is duplicated"),
    NOT_EXIST_OFFICE_NO(400,"BAD_REQUEST", "B008", "Broker office number is not exist"),

    // mapper CRUD failed
    OFFICE_DELETION_FAILED(500,"INTERNAL_SERVER_ERROR","B009", "Broker office deletion is failed"),
    OFFICE_MODIFICATION_FAILED(500,"INTERNAL_SERVER_ERROR","B009", "Broker office modification is failed"),

    // User
    NOT_EXIST_USER_ID(400, "BAD_REQUEST", "U000", "User Id is not exist"),
    NOT_EXIST_USER_NO(400, "BAD_REQUEST", "U000", "User number is not exist"),

    // Realty
    BUILDING_ADDRESS_DUPICATION(400,"BAD_REQUEST", "R001", "Building address is duplicated"),
    NOT_EXIST_BUILDING_ADDRESS(400, "BAD_REQUEST", "B000", "Building address is not exist"),
    NOT_EXIST_BUILDING_NO(400, "BAD_REQUEST", "B000", "Building number is not exist"),
    NOT_EXIST_OPTION_NO(400, "BAD_REQUEST", "B000", "Option number is not exist"),
    NOT_EXIST_POST_NO(400, "BAD_REQUEST", "B000", "POST number is not exist"),
    INVALID_TYPE(400,"BAD_REQUEST", "B007", "Parameter(type) is invalid [basic,option,post,all]"),
    MAINTAIN_NAME_DUPLICATION(400,"BAD_REQUEST", "R002", "Maintain name is duplicated"),
    
    NOT_EXISTED_MAINTAIN_ITEM(400,"BAD_REQUEST", "R003", "Maintain item is not existed"),
    
    ;
    

    private int statusCode;
    private final String statusText;
    private final String exceptionCode;
    private final String message;
}