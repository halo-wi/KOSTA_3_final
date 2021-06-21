package com.kosta.KOSTA_3_final.model.member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO {
    private String address;
    private String title;
    private String message;
}