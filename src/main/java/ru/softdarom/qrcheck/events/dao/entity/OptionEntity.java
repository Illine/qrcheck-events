package ru.softdarom.qrcheck.events.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.softdarom.qrcheck.events.util.JsonHelper;

import javax.persistence.*;

@Generated
@Data
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = {"id", "name"})
@Entity
@Table(name = "options")
@SQLDelete(sql = "UPDATE options SET active = false, updated = current_timestamp WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "active = true")
public class OptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "optionSeqGenerator")
    @SequenceGenerator(name = "optionSeqGenerator", sequenceName = "option_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @Override
    public String toString() {
        return JsonHelper.asJson(this);
    }
}