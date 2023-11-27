package Trabalho_Pratico;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {
    // Configuração do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/Equipamentos";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public RemoteObject() throws RemoteException {
        // Construtor padrão
    }

    @Override
    public void inserirRecurso(EquipamentoApoio recurso) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "INSERT INTO Apoio (id, descricao, disponivel, requisitado, tipoEquipamento, detalhesEquipamento) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, recurso.getId());
                statement.setString(2, recurso.getDescricao());
                statement.setBoolean(3, recurso.isDisponivel());
                statement.setBoolean(4, recurso.isRequisitado());
                statement.setString(5, recurso.getTipoEquipamento());
                statement.setString(6, recurso.getDetalhesEquipamento());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EquipamentoApoio> consultarRecursos(String descricao) throws RemoteException {
        List<EquipamentoApoio> recursos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT * FROM Apoio WHERE descricao LIKE ? AND disponivel = true";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + descricao + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String descricaoRecurso = resultSet.getString("descricao");
                        boolean disponivel = resultSet.getBoolean("disponivel");
                        boolean requisitado = resultSet.getBoolean("requisitado");
                        String tipoEquipamento = resultSet.getString("tipoEquipamento");
                        String detalhesEquipamento = resultSet.getString("detalhesEquipamento");

                        EquipamentoApoio recurso = new EquipamentoApoio(id, descricaoRecurso, disponivel, requisitado, tipoEquipamento, detalhesEquipamento);
                        recursos.add(recurso);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recursos;
    }

    @Override
    public String requisitarRecurso(int id, ClienteCallback callback) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String checkQuery = "SELECT * FROM Apoio WHERE id = ? AND disponivel = true";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setInt(1, id);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // O recurso está disponível, pode ser requisitado
                        String updateQuery = "UPDATE Apoio SET disponivel = false, requisitado = true WHERE id = ?";
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                            updateStatement.setInt(1, id);
                            int rowsAffected = updateStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                // Realiza o callback para notificar o cliente sobre a reserva
                                callback.notificarRecursoDisponivel();
                                return "Recurso requisitado com sucesso.";
                            } else {
                                return "Erro ao requisitar o recurso.";
                            }
                        }
                    } else {
                        return "O recurso não está disponível para ser requisitado.";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao requisitar o recurso.";
        }
    }


    @Override
    public List<EquipamentoApoio> listarRecursos() throws RemoteException {
        List<EquipamentoApoio> recursos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "SELECT * FROM Apoio WHERE disponivel = true";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String descricao = resultSet.getString("descricao");
                        boolean disponivel = resultSet.getBoolean("disponivel");
                        boolean requisitado = resultSet.getBoolean("requisitado");
                        String tipoEquipamento = resultSet.getString("tipoEquipamento");
                        String detalhesEquipamento = resultSet.getString("detalhesEquipamento");

                        EquipamentoApoio recurso = new EquipamentoApoio(id, descricao, disponivel, requisitado, tipoEquipamento, detalhesEquipamento);
                        recursos.add(recurso);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recursos;
    }

    @Override
    public String devolverRecurso(int id) throws RemoteException {
        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            String query = "UPDATE Apoio SET disponivel = true, requisitado = false WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return "Recurso devolvido com sucesso. Agora está disponível novamente.";
                } else {
                    return "Você não pode devolver este recurso. Ele não foi requisitado por você.";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao devolver o recurso.";
        }
    }

    // Outras operações...
}
