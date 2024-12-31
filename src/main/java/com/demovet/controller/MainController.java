package com.demovet.controller;

import com.demovet.entity.*;
import com.demovet.repository.UsuarioRepository;
import com.demovet.service.*;
import com.demovet.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IProductoService productoService;

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

    @PostMapping("/orders/{idUsuario}")
    public Orden crearOrden(@RequestBody Orden orden, @PathVariable Long idUsuario){
        return ordenService.crearOrden(orden,idUsuario);
    }

    @GetMapping("/orders")
    public List<Orden> listarOrdenes(){
        return ordenService.consultarOrdenes();
    }

    @PostMapping("/add-product/{idOrden}/{idProducto}/{cantidad}")
    public Orden addProductToOrden(@PathVariable Long idOrden,@PathVariable Long idProducto,@PathVariable int cantidad){
        return ordenService.addProducto(idOrden,idProducto,cantidad);
    }

    @PostMapping("/products")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }

    @GetMapping("/products")
    public List<Producto> listarProductos(){
        return productoService.findAllProductos();
    }
}
