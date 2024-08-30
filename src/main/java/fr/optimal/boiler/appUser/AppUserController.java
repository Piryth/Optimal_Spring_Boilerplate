package fr.optimal.optimalshop.appUser;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shape-monitor/api/user")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/get")
    public ResponseEntity<AppUserDto> getAuthenticatedUser() {
        return ResponseEntity.ok(appUserService.getAuthenticatedUser());
    }

    @PostMapping("/update")
    public ResponseEntity<AppUserDto> updateAuthenticatedUser(
            @RequestBody @NonNull AppUserDto userDto
    ) {
            return ResponseEntity.ok(appUserService.updateAuthenticatedUser(userDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAuthenticatedUser(){
        return ResponseEntity.ok(appUserService.deleteAuthenticatedUser());
    }

}
