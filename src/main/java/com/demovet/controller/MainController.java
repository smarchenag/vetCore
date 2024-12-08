package com.demovet.controller;

import com.demovet.entity.AuthenticationRequest;
import com.demovet.entity.Mascota;
import com.demovet.entity.Usuario;
import com.demovet.repository.UsuarioRepository;
import com.demovet.service.CustomUserDetailService;
import com.demovet.service.IMascotaService;
import com.demovet.service.IUsuarioService;
import com.demovet.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vet")
public class MainController {

    @Autowired
    private final IUsuarioService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public MainController(IUsuarioService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity guardarUsuario(@RequestBody Usuario u){
        service.guardarUsuario(u);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping("/list-users")
    public ResponseEntity<?> listarUsuarios(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/save-mascota/{idUsuario}")
    public ResponseEntity<?> saveMascotaToUser(@PathVariable Long idUsuario,@RequestBody Mascota mascota){
        return ResponseEntity.ok(service.crearMascota(idUsuario,mascota));
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Usuario user) {
        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
