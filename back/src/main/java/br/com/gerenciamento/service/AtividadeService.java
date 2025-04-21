package br.com.gerenciamento.service;

import br.com.gerenciamento.domain.Atividade;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AtividadeService {
    private List<Atividade> atividades = new ArrayList<>();

    public List<Atividade> listarTodas() {
        return atividades;
    }

    public void salvar(Atividade atividade) {
        atividades.add(atividade);
    }
}