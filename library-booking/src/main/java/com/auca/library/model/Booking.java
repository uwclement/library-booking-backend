package com.auca.library.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.RESERVED;

    private boolean checkedIn = false;
    
    private LocalDateTime checkedInTime;

    private LocalDateTime checkedOutTime;

    private String notes;
    
    @Column(name = "warning_sent")
    private Boolean warningSent = false;


      // Check-in and check-out times
      private LocalDateTime checkinTime;
      private LocalDateTime checkoutTime;
  
      // Cancellation details
      private LocalDateTime cancellationTime;
      private String cancellationReason;
    
    // For tracking extension requests
    private boolean extensionRequested = false;
    private boolean extended = false;
    private LocalDateTime extensionNotifiedAt;
    private LocalDateTime extensionRespondedAt;

    public enum BookingStatus {
        RESERVED,       // Initial state
        CHECKED_IN,     // User has checked in
        COMPLETED,      // Booking is completed
        CANCELLED,      // User cancelled the booking
        NO_SHOW         // User didn't check in
    }
    
    public Booking(User user, Seat seat, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.seat = seat;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}