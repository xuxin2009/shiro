package com.sean.param;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2019/4/6.
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String name;

    @NotNull
    private Integer id;
}
