package com.astar.realty.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

//custom exception errocode 정의
@AllArgsConstructor
@Getter
public enum ErrorCode {

    // // Common
    // INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    // METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    // HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    // // Member
    // EMAIL_DUPLICATION(400, "M001", "Email is Duplicated"),

    // Broker
    ID_DUPLICATTION(400,"BAD_REQUEST", "B001 Broker account Id is duplicated"),
    INVALID_ID(400, "BAD_REQUEST", "B002 Broker account Id is invalid"),
    INVALID_PWD(400, "BAD_REQUEST", "B003 Broker account Pwd is invalid"),
    LOGIN_INPUT_INVALID(401, "UNAUTHORIZED", "B004 Broker login input values are invalid"),
    AES_ALGORITHM_ERROR(500,"INTERNAL_SERVER_ERROR", "B005 AES algorithm not passed"),
    INVALID_STATUS(400,"BAD_REQUEST", "B006 Parameter(status) leaving account is invalid [1,2,3,4]"),
    INVALID_VALUE(400,"BAD_REQUEST", "B007 Parameter(value) leaving account is invalid"),

    NOT_EXISTED_OFFICE(200,"No content", "B008 Broker office is not existed"),
    OFFICE_DELETION_FAILED(500,"INTERNAL_SERVER_ERROR","B009 Broker office deletion is failed"),

    // Realty
    REALTY_ADDRESS_DUPICATION(202,"ACCEPTED", "R001 realty address is duplicated"),
    EXISTED_MAINTAIN_ITEM(200,"No content", "R002 realty maintain item is existed yet"),
    NOT_EXISTED_MAINTAIN_ITEM(200,"No content", "R003 realty maintain item is not existed"),
    INVALID_ADDRESS(400,"BAD_REQUEST", "R004 realty address is invalid"),
    NOT_EXISTED_BUILDING(200,"No content", "R002 building is not existed"),
    ;
    

    private int status;
    private final String code;
    private final String message;
}