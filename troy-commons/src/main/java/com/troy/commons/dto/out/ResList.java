package com.troy.commons.dto.out;

import lombok.*;

import java.util.List;

/**
 * 通用list对象返回
 *
 * @author dp
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResList<DT extends ResData> extends ResData {

    /**
     * 通用List
     */
    private List<DT> list;
}
