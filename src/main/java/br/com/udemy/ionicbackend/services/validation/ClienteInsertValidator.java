package br.com.udemy.ionicbackend.services.validation;

import br.com.udemy.ionicbackend.domain.Cliente;
import br.com.udemy.ionicbackend.domain.enums.TipoCliente;
import br.com.udemy.ionicbackend.dto.ClienteNewDTO;
import br.com.udemy.ionicbackend.repositories.ClienteRepository;
import br.com.udemy.ionicbackend.services.exceptions.FieldMessage;
import br.com.udemy.ionicbackend.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> erros = new ArrayList<>();

        // incluir os testes aqui, inserindo erros na lista

        if (dto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo()) &&
                !BR.isValidCpf(dto.getCpfOuCnpj())) {
            erros.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
        }

        if (dto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo()) &&
                !BR.isValidCnpj(dto.getCpfOuCnpj())) {
            erros.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
        }

        Cliente cliente = clienteRepository.findByEmail(dto.getEmail());
        if (cliente != null) {
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