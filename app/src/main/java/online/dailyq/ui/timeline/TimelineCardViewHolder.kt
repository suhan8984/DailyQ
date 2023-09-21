package online.dailyq.ui.timeline

import androidx.recyclerview.widget.RecyclerView
import online.dailyq.R
import online.dailyq.api.response.Question
import online.dailyq.databinding.ItemTimelineCardBinding
import java.time.format.DateTimeFormatter

import android.content.Intent
import online.dailyq.ui.details.DetailsActivity
import online.dailyq.db.entity.QuestionEntity

class TimelineCardViewHolder(val binding: ItemTimelineCardBinding): RecyclerView.ViewHolder(binding.root) {
    companion object {
        val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy. M. d.")
    }
    fun bind(question: QuestionEntity) {
        binding.date.text = DATE_FORMATTER.format(question.id)
        binding.question.text = question.text ?: ""

        binding.answerCount.text = if (question.answerCount > 0) {
            binding.root.context.getString(R.string.answer_count_format, question.answerCount)
        } else {
            binding.root.context.getString(R.string.no_answer_yet)
        }

        binding.card.setOnClickListener {
            val context = binding.root.context
            context.startActivity(Intent(context, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.EXTRA_QID, question.id)
            })
        }
    }
}