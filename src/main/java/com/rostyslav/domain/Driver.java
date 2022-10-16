/**
 * @author RoSteik (Telegram: @RoSteik)
 * Project: lab4-db
 * Package: com.rostyslav.domain
 * Class: Driver
 */

package com.rostyslav.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Driver {
    private Integer id;
    private String name;
    private Integer rating;
    private Integer completedOrders;
    private Integer isVacant; // 0 - false, 1 - true
    private String userName;
}
