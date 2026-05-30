package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbChecker2 {
    public static void main(String[] args) {
        try (Connection conn = ConexaoDB.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT r.*, s.nome AS sala_nome, d.nome AS docente_nome FROM reserva r INNER JOIN sala s ON s.id = r.sala_id INNER JOIN docente d ON d.id = r.docente_id")) {
            
            System.out.println("Reservations in DB (with teacher and room join):");
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.printf("ID: %d, Sala: %s, Docente: %s, Data: %s, Inicio: %s, Fim: %s, Status: %s, Finalidade: %s\n",
                    rs.getInt("id"),
                    rs.getString("sala_nome"),
                    rs.getString("docente_nome"),
                    rs.getDate("data_reserva"),
                    rs.getTime("hora_inicio"),
                    rs.getTime("hora_fim"),
                    rs.getString("status"),
                    rs.getString("finalidade")
                );
            }
            if (count == 0) {
                System.out.println("No reservations found matching the JOIN query.");
            }
            
            try (ResultSet rsRaw = stmt.executeQuery("SELECT * FROM reserva")) {
                System.out.println("Raw reservations in DB:");
                int rawCount = 0;
                while (rsRaw.next()) {
                    rawCount++;
                    System.out.printf("ID: %d, SalaID: %d, DocenteID: %d, Status: %s, Data: %s\n",
                        rsRaw.getInt("id"),
                        rsRaw.getInt("sala_id"),
                        rsRaw.getInt("docente_id"),
                        rsRaw.getString("status"),
                        rsRaw.getDate("data_reserva")
                    );
                }
                if (rawCount == 0) {
                    System.out.println("No raw reservations found in the table.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
