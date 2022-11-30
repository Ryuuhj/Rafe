package com.project.rafe.domain.storage.dto;

import com.project.rafe.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddStorageReqDto {
    @NotNull
    private Long userId;
    @NotEmpty
    private List<Long> igIdList;

    @Builder
    public AddStorageReqDto(Long userId, List<Long> igIdList) {
        this.userId = userId;
        this.igIdList = igIdList;
    }
}
