/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {

            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            return true;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar: " + e.getMessage());

            return false;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        listagem.clear();

        String sql = "SELECT * FROM produtos";

        try {

            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Erro ao listar: " + e.getMessage());

        }

        return listagem;

    }

}

