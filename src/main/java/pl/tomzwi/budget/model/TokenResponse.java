package pl.tomzwi.budget.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String username;
    private String accessToken;
    private String expires;
    private List<String> roles;
}
