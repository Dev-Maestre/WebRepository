package com.example.dsystemserver.Models;


//import org.mindrot.jbcrypt.BCrypt;

import com.example.dsystemserver.System.Connection.JWTManager;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver {

    private Connection connection;
    private final Object lock = new Object();

    public DatabaseDriver() throws SQLException {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:db.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // User CRUD
    public void addCandidate(User user) throws SQLException {
        System.out.println("Adding Candidate");
        //System.out.println(user.getID());

        String sql = "INSERT INTO candidate (email, password, name) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRecruiter(User user) throws SQLException {
        System.out.println("Adding Recruiter");
        String sql = "INSERT INTO recruiter (email, password, name, industry, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getIndustry());
            pstmt.setString(5, user.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCandidate(User user) throws SQLException { // é pq meu cliente funcionou td no servidor do cainan n sei como
        synchronized (lock) {
            String sql = "UPDATE candidate SET email = ?, password = ?, name = ?  WHERE id = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getUsername());
                pstmt.setInt(4, user.getID());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //String sql = "INSERT INTO recruiter (email, password, name, industry, description, id) VALUES (?, ?, ?, ?, ?, ?)";

    public void updateRecruiter(User user) throws SQLException {
        synchronized (lock) {
            String sql = "UPDATE recruiter SET email = ?, password = ?, name = ?, industry = ?, description = ?, WHERE id = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setString(1, user.getEmail());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getUsername());
                pstmt.setString(4, user.getIndustry());
                pstmt.setString(5, user.getDescription());
                pstmt.setInt(6, user.getID());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void  deleteCandidate(User user) throws SQLException {
        synchronized (lock) {
            String sql = "DELETE FROM candidate WHERE id = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setInt(1, user.getID());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> deleteRecruiter(User user) throws SQLException {
        synchronized (lock) {
            String sql = "DELETE * FROM recruiter";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                ResultSet resultSet = pstmt.executeQuery();
                List<User> clientList = new ArrayList<>();
                while (resultSet.next()) {
                    User client = getUserFromResultSet(resultSet);
                    if (client != null) {
                        clientList.add(client);
                    }
                }
                return clientList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public User getCandidateByID(long id) throws SQLException {
        synchronized (lock) {
            String sql = "SELECT * FROM candidate WHERE id = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    return null;
                }
                return getUserFromResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getRecruiterByID(long id) throws SQLException {
        synchronized (lock) {
            String sql = "SELECT * FROM recruiter WHERE id = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    return null;
                }
                return getUserFromResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getCandidateByToken(String token) throws SQLException {
            return getCandidateByID(Integer.parseInt(JWTManager.getUserIdFromToken(token)));
    }

    public User getRecruiterByToken(String token) throws SQLException {
        return getRecruiterByID(Integer.parseInt(JWTManager.getUserIdFromToken(token)));
    }

    public User getCandidateLogin(String email, String password) throws SQLException {
        synchronized (lock) {
            String sql = "SELECT * FROM candidate WHERE email = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setString(1, email);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    return null;
                }
                User user = getUserFromResultSet(resultSet);
                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getRecruiterLogin(String email, String password) throws SQLException {
        synchronized (lock) {
            String sql = "SELECT * FROM recruiter WHERE email = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setString(1, email);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    return null;
                }
                User user = getRecruiterFromResultSet(resultSet);
                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return new User(resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getInt("id"));
    }

    public User getRecruiterFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null)
            return null;
        return new User(resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getString("industry"), resultSet.getString("description"), resultSet.getInt("id"));
    }


    public User getCandidateByEmail(String email) throws SQLException {
        synchronized (lock) {
            String sql = "SELECT * FROM candidate WHERE email = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setString(1, email);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    return null;
                }
                return getUserFromResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public User getRecruiterByEmail(String email) throws SQLException {
        synchronized (lock) {
            String sql = "SELECT * FROM recruiter WHERE email = ?";
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                pstmt.setString(1, email);
                ResultSet resultSet = pstmt.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    return null;
                }
                return getUserFromResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
