package com.the_boys.mini_banck.repository;

import com.the_boys.mini_banck.model.Account;
import com.the_boys.mini_banck.model.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //Buscamos por el numero visible al cliente(no exponer el id interno)
    Optional<Account>findByAccountNumber(String accountNumber);

    //con el AccountNumber,para verificar que no se repita.
    public boolean existsByAccountNumber(String accountNumber);

    //Cuentas de un usuario("user.id" se escribe User_Id)
    List<Account> findByUser_Id(Long id);
    List<Account>findByUser_IdAndStatus(Long id, AccountStatus status);



}
