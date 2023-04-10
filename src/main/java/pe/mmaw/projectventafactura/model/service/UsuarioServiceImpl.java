package pe.mmaw.projectventafactura.model.service;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pe.mmaw.projectventafactura.model.dao.IRoleDAO;
import pe.mmaw.projectventafactura.model.dao.IUsuarioDAO;
import pe.mmaw.projectventafactura.model.entity.Producto;
import pe.mmaw.projectventafactura.model.entity.Role;
import pe.mmaw.projectventafactura.model.entity.Usuario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private UpdateFile updateFile;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUsuarioById(Integer id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {

        if (usuario.getId() != null) {
            Usuario user = usuarioDAO.findById(usuario.getId()).get();

            user.getRole().setRol(usuario.getRole().getRol());
            usuario.setRole(user.getRole());

            usuario.setCreatedAt(user.getCreatedAt());
            usuario.setUpdatedAt(LocalDateTime.now());

        } else {
            Role role = new Role(
                    usuario.getRole().getUsername(),
                    usuario.getRole().getPassword(),
                    usuario.getRole().getRol(),
                    usuario);

            usuario.setRole(role);
        }

        return usuarioDAO.save(usuario);
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuarioDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario fetchByIdWithRole(Integer id) {
        return usuarioDAO.fetchByIdWithRole(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByDniList(String term) {
        return usuarioDAO.findByDniLikeIgnoreCase("%"+term+"%");
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByDni(String dni) {
        return usuarioDAO.findByDni(dni);
    }
}
