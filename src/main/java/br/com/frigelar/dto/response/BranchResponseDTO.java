package br.com.frigelar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponseDTO {

    private Long id;
    private String code;
    private String location;
    private String city;
    private String uf;
    private Boolean isActive;
}