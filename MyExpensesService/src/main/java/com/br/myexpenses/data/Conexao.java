package com.br.myexpenses.data;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexao {
	private String url;
    private String usr;
    private String pass;
    
    public Conexao() {     
    	url = "jdbc:postgresql://localhost:5433/myexpenses"; //incica o caminho do banco de dados
        usr = "postgres"; // aqui vai o nome usuario que vc quer acessar
        pass = "gabrielpgadmin2018"; // aqui a senha do seu banco
    }
    
    private Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
           
            Connection con = DriverManager.getConnection(url, usr, pass);
            return con;
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "O driver não foi encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
 
    public int executeUpdate(String query){
        try{
            Connection con = getConnection();
            Statement stm = con.createStatement();
            System.out.println(query);
            int res=stm.executeUpdate(query);
            con.close();
            return res;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
 
    public ResultSet executeQuery(String query){
        try{
            Connection con = getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            //con.close();
            return rs;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n"+ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
