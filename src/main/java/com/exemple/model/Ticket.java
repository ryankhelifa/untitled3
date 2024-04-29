package com.exemple.model;

import com.exemple.model.Diffusion;

public class Ticket {
    private Long ticketId;
    private Long userId;
    private Long diffusionId;
    private Diffusion diffusion;


    public Ticket() {
    }

    public Ticket(Long ticketId, Long userId, Long diffusionId) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.diffusionId = diffusionId;
    }

    public Ticket(Long ticketId, Long userId,  Diffusion diffusion)  {
        this.ticketId = ticketId;
        this.userId = userId;
        this.diffusion = diffusion;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDiffusionId() {
        return diffusionId;
    }

    public void setDiffusionId(Long diffusionId) {
        this.diffusionId = diffusionId;
    }

    public Diffusion getDiffusion() {
        return diffusion;
    }

    public void setDiffusionId(Diffusion diffusion) {
        this.diffusion = diffusion;
    }

    public void setDiffusion(Diffusion diffusion) {
        this.diffusion = diffusion;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", userId=" + userId +
                ", " + diffusion.toString() +
                '}';
    }



}