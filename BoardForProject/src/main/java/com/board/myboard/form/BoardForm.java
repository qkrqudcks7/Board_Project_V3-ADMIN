package com.board.myboard.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BoardForm {

    private String nickname;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    private String imgUrl;


}
