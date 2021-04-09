package br.com.udemy.ionicbackend.services.validation;

import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.dto.ClienteDTO;
import br.com.udemy.ionicbackend.repositories.ClienteRepository;
import br.com.udemy.ionicbackend.services.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> erros = new ArrayList<>();

        // incluir os testes aqui, inserindo erros na lista

        Cliente cliente = clienteRepository.findByEmail(dto.getEmail());
        if (cliente != null && !cliente.getId().equals(uriId)) {
            erros.add(new FieldMessage("Email", "E-mail já existente!"));
        }

        for (FieldMessage e : erros) { // precisa fazer isso pra transformar a lista de erros personalizados de forma que o framework consiga entender
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return erros.isEmpty();
    }
}