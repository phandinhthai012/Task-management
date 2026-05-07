package com.api.taskmanagementapi.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter  implements Filter {

    private JwtUtil jwtUtil;

    // danh sách các URL công khai không cần xác thực
    private static final List<String> PUBLIC_URLS = List.of(
            "/api/auth",
            "/api/healthCheck"

    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorization = httpServletRequest.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")) {
            String header = authorization.substring(7);
            try {
               Claims token = jwtUtil.parseToken(header);

            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid token");
            }
        }
        // DÒNG QUAN TRỌNG BỊ THIẾU: Cho phép request đi tiếp tới Controller
        filterChain.doFilter(servletRequest, servletResponse);
    }
}



//public class JwtFilter extends OncePerRequestFilter { // Đổi sang OncePerRequestFilter
//
//    // BẮT BUỘC phải có chữ 'final' thì @RequiredArgsConstructor mới hoạt động
//    private final JwtUtil jwtUtil;
//
//    // Danh sách các API công khai (Public) không cần kiểm tra Token
//    private static final List<String> PUBLIC_URLS = List.of(
//            "/api/auth",
//            "/api/healthCheck"
//            // Bạn có thể thêm "/api/users" vào đây nếu API tạo user là để Đăng Ký tự do
//    );
//
//    // Hàm này giúp loại bỏ các API không cần lọc
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        String path = request.getServletPath();
//        // Nếu URL của request bắt đầu bằng một trong các chuỗi ở PUBLIC_URLS -> Bỏ qua Filter này
//        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String authorization = request.getHeader("Authorization");
//
//        if (authorization != null && authorization.startsWith("Bearer ")) {
//            String token = authorization.substring(7);
//            try {
//                // Parse thử token xem có hợp lệ và chưa hết hạn không
//                Claims claims = jwtUtil.parseToken(token);
//
//                // (Sau này chúng ta sẽ viết code lưu thông tin user vào Spring Security Context ở đây)
//
//            } catch (Exception e) {
//                // Nếu token sai, sửa, hoặc hết hạn -> Chặn ngay lập tức và trả về lỗi 401
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid or expired token");
//                return; // Dừng không cho đi tiếp vào Controller
//            }
//        }
//
//        // Cho phép request đi tiếp tới Filter tiếp theo hoặc Controller
//        filterChain.doFilter(request, response);
//    }
//}