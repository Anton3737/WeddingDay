package WeddingDayRegister.WeddingDay.service;

import WeddingDayRegister.WeddingDay.model.RSVPForm;
import WeddingDayRegister.WeddingDay.repository.RsvpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RsvpDaoImpl implements RsvpDao {

    @Autowired
    private RsvpRepository rsvpRepository;


    @Override
    public List<RSVPForm> findAllGuests() {
        try {
            return rsvpRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public RSVPForm addGuest(RSVPForm rsvpForm) {
        return rsvpRepository.save(rsvpForm);
    }

    @Override
    public void deleteById(Long id) {
        rsvpRepository.deleteById(id);
    }

    @Override
    public RSVPForm getById(Long id) {
        return rsvpRepository.findById(id).orElse(null);
    }

    @Override
    public RSVPForm update(Long id, RSVPForm update) {
        return rsvpRepository.findById(id).map(existingGuest -> {
            existingGuest.setName(update.getName());
            existingGuest.setSecondName(update.getSecondName());
            existingGuest.setPresence(update.getPresence());
            existingGuest.setGuest(update.getGuest());
            existingGuest.setBeverage(update.getBeverage());
            return rsvpRepository.save(existingGuest);
        }).orElse(null);
    }

}
